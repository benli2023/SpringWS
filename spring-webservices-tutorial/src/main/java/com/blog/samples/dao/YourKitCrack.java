package com.blog.samples.dao;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Locale;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtField;
import javassist.CtMethod;
import javassist.CtNewMethod;
import javassist.NotFoundException;
import javassist.bytecode.AccessFlag;
import javassist.bytecode.ClassFile;
import javassist.bytecode.ClassFilePrinter;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.filefilter.SuffixFileFilter;
import org.apache.commons.io.filefilter.TrueFileFilter;

import com.yourkit.h.DecrytedInputStream;

public class YourKitCrack {

	private static final String USER_NAME = "NICK ZHANG";

	private static final int LIC_TYPE = 1;

	private static final int AUTH_TYPE = 4051;

	private static final String AUTH_CODE = "TS11111111111-TOT123131313";

	private static final String BLANK = "";

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		checkLicense();
	}

	private static void checkLicense() throws UnknownHostException {
		MulticastSocket multicastsocket;
		InetAddress inetaddress;

		InetAddress localhost = InetAddress.getLocalHost();

		try {
			inetaddress = InetAddress.getByName("230.0.0.3");
			multicastsocket = new MulticastSocket(9879);
			multicastsocket.joinGroup(inetaddress);
		} catch (IOException ioexception) {
			ioexception.printStackTrace();
			inetaddress = null;
			multicastsocket = null;
		}

		try {
			String s = (new StringBuilder()).append(AUTH_CODE).append("|").append(localhost).toString();
			byte[] sendbytes = s.getBytes("utf-8");
			multicastsocket.send(new DatagramPacket(sendbytes, sendbytes.length, inetaddress, 9879));
		} catch (IOException ioexception) {
			ioexception.printStackTrace();
		}

		byte abyte0[] = new byte[1024];
		do {

			DatagramPacket datagrampacket = new DatagramPacket(abyte0, abyte0.length);
			try {
				multicastsocket.receive(datagrampacket);
			} catch (IOException e) {
				e.printStackTrace();
				continue;
			}

			InetAddress inetaddress1 = datagrampacket.getAddress();
			if (inetaddress1.equals(localhost))
				continue;

			String s;
			try {
				s = (new String(datagrampacket.getData(), "utf-8")).trim();
			} catch (UnsupportedEncodingException unsupportedencodingexception) {
				unsupportedencodingexception.printStackTrace();
				continue;
			}
			System.out.println(s);

		} while (true);

	}

	private static void changeNative() throws IOException {
		changeNativeClass("{ return getLicenseBytes();}");
	}

	private static byte[] getLicenseBytes() throws IOException {
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream(128);
		writeString(outputStream, USER_NAME);
		writeInt(outputStream, LIC_TYPE);
		writeInt(outputStream, AUTH_TYPE);
		writeString(outputStream, AUTH_CODE);
		writeString(outputStream, formateDate(new Date()));// issue date
		writeString(outputStream, BLANK);// expire date
		writeString(outputStream, BLANK);
		writeString(outputStream, BLANK);
		byte[] intbytes = outputStream.toByteArray();
		return intbytes;
	}

	private static void printLicense(byte[] licenseBytes) throws IOException {
		ByteArrayInputStream inputstream = new ByteArrayInputStream(licenseBytes);
		int length = licenseBytes.length;
		String userName = readString(inputstream, length);
		int licType = readInt(inputstream);
		int authType = readInt(inputstream);
		String authCode = readString(inputstream, length);
		String issueDate = readString(inputstream, length);
		String exprieDate = readString(inputstream, length);
		String pad1 = readString(inputstream, length);
		String pad2 = readString(inputstream, length);
		StringBuilder builder = new StringBuilder(128);
		builder.append(userName + "\n");
		builder.append(licType + "\n");
		builder.append(authType + "\n");
		builder.append(authCode + "\n");
		builder.append(issueDate + "\n");
		builder.append(exprieDate + "\n");
		builder.append(pad1 + "\n");
		builder.append(pad2);
		System.out.print(builder.toString());
	}

	private static void changeNativeClass(String methodBody) throws IOException {
		String clzname = "com.yourkit.Natives";
		ClassPool pool = ClassPool.getDefault();
		addImports(pool);
		try {
			CtClass cc = pool.get(clzname);
			CtMethod ctMethod = cc.getDeclaredMethod("decipher2");
			int accflags = ctMethod.getMethodInfo2().getAccessFlags();
			accflags = (accflags & ~(AccessFlag.NATIVE));
			ctMethod.getMethodInfo().setAccessFlags(accflags);
			addFields(cc);
			addMethods(cc);
			ctMethod.setBody(methodBody);
			cc.freeze();
			cc.writeFile();
		} catch (NotFoundException e) {
			e.printStackTrace();
		} catch (CannotCompileException e) {
			e.printStackTrace();
		}
	}

	private static void addImports(ClassPool pool) {
		pool.importPackage("java.io");
		pool.importPackage("java.util");
		pool.importPackage("java.text");
	}

	private static void addMethods(CtClass cc) throws CannotCompileException {
		// writeInt method
		addMethod("private static void writeInt(ByteArrayOutputStream outputStream, int size) {\r\n" + "		int i = (size & 0xff);\r\n" + "		int j = (size >> 8 & 0xff);\r\n"
				+ "		int k = (size >> 16 & 0xff);\r\n" + "		int l = (size >> 24 & 0xff);\r\n" + "		outputStream.write(i);\r\n" + "		outputStream.write(j);\r\n" + "		outputStream.write(k);\r\n"
				+ "		outputStream.write(l);\r\n" + "	}", cc);

		// writeString method
		addMethod("private static void writeString(ByteArrayOutputStream outputStream, String str) throws IOException {\r\n" + "		if (str == null) {\r\n"
				+ "			throw new IllegalArgumentException(\"input string must not be empty.\");\r\n" + "		}\r\n" + "		int length = str.length();\r\n" + "		writeInt(outputStream, length);\r\n"
				+ "		if (length > 0) {\r\n" + "			outputStream.write(str.getBytes());\r\n" + "		}\r\n" + "	}", cc);

		// formateDate
		addMethod("private static String formateDate(Date date) {\r\n" + "		SimpleDateFormat simpledateformat = new SimpleDateFormat(\"yyyyMMdd\", Locale.US);\r\n"
				+ "		return simpledateformat.format(date);\r\n" + "	}", cc);

		// getLicenseBytes method
		addMethod("private static byte[] getLicenseBytes() throws IOException {\r\n" + "		ByteArrayOutputStream outputStream = new ByteArrayOutputStream(128);\r\n"
				+ "		writeString(outputStream, USER_NAME);\r\n" + "		writeInt(outputStream, LIC_TYPE);\r\n" + "		writeInt(outputStream, AUTH_TYPE);\r\n"
				+ "		writeString(outputStream, AUTH_CODE);\r\n" + "		writeString(outputStream, formateDate(new Date()));// issue date\r\n" + "		writeString(outputStream, BLANK);// expire date\r\n"
				+ "		writeString(outputStream, BLANK);\r\n" + "		writeString(outputStream, BLANK);\r\n" + "		byte[] intbytes = outputStream.toByteArray();\r\n" + "		return intbytes;\r\n" + "	}", cc);

	}

	private static void addMethod(String methodBody, CtClass cc) throws CannotCompileException {
		StringBuilder builder = new StringBuilder();
		builder.append(methodBody);
		CtMethod m = CtNewMethod.make(builder.toString(), cc);
		cc.addMethod(m);
	}

	private static void addFields(CtClass cc) throws CannotCompileException {
		addField("private static final String USER_NAME = \"NICK ZHANG\";", cc);
		addField("private static final int LIC_TYPE = 1;", cc);
		addField("private static final int AUTH_TYPE = 4051;", cc);
		addField("private static final String AUTH_CODE = \"TS11111111111-TOT123131313\";", cc);
		addField("private static final String BLANK = \"\";", cc);
	}

	private static void addField(String fieldBody, CtClass cc) throws CannotCompileException {
		CtField f = CtField.make(fieldBody, cc);
		cc.addField(f);
	}

	private static String formateDate(Date date) {
		SimpleDateFormat simpledateformat = new SimpleDateFormat("yyyyMMdd", Locale.US);
		return simpledateformat.format(date);
	}

	private static void writeInt(ByteArrayOutputStream outputStream, int size) {
		int i = (size & 0xff);
		int j = (size >> 8 & 0xff);
		int k = (size >> 16 & 0xff);
		int l = (size >> 24 & 0xff);
		outputStream.write(i);
		outputStream.write(j);
		outputStream.write(k);
		outputStream.write(l);
	}

	private static void writeString(ByteArrayOutputStream outputStream, String str) throws IOException {
		if (str == null) {
			throw new IllegalArgumentException("input string must not be empty.");
		}
		int length = str.length();
		writeInt(outputStream, length);
		if (length > 0) {
			outputStream.write(str.getBytes());
		}
	}

	private static String readString(ByteArrayInputStream inputstream, int length) throws IOException {
		String s;
		if (inputstream == null)
			throw new IllegalArgumentException("Parameter 1 must not be null");
		int j = readInt(inputstream);
		if (j > length)
			throw new IndexOutOfBoundsException("index:" + j + " ,length:" + length);
		byte abyte0[] = new byte[j];
		inputstream.read(abyte0);
		s = new String(abyte0, "utf-8");
		return s;
	}

	private static int readInt(ByteArrayInputStream inputstream) throws IOException {
		if (inputstream == null) {
			throw new IllegalArgumentException("Parameter 1 must not be null");
		} else {
			int i = inputstream.read();
			int j = inputstream.read();
			int k = inputstream.read();
			int l = inputstream.read();
			return i | j << 8 | k << 16 | l << 24;
		}
	}

	private static void decripted() throws IOException {
		String dir = "D:\\Program Files\\YourKit Java Profiler 2013 build 13074\\yjp-encryted";
		SuffixFileFilter suffixFileFilter = new SuffixFileFilter("_");
		Collection<File> files = FileUtils.listFiles(new File(dir), suffixFileFilter, TrueFileFilter.INSTANCE);
		for (File file : files) {
			InputStream in = new DecrytedInputStream(new FileInputStream(file));
			String newFileName = file.getParentFile().getAbsolutePath() + "/" + file.getName().substring(0, file.getName().length() - 1);
			OutputStream out = new FileOutputStream(new File(newFileName));
			IOUtils.copy(in, out);
			in.close();
			out.close();
			file.delete();
		}
	}

	private static void dump(String className) {
		ClassFile w = new ClassFile(false, className, null);
		PrintWriter out = new PrintWriter(System.out, true);
		out.println("*** constant pool ***");
		w.getConstPool().print(out);
		out.println();
		out.println("*** members ***");
		ClassFilePrinter.print(w, out);
	}
}
