package com.blog.samples.components;

import java.lang.reflect.Method;

import javax.xml.namespace.QName;
import javax.xml.transform.TransformerFactory;

import org.springframework.beans.BeansException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.ws.context.MessageContext;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.mapping.AbstractAnnotationMethodEndpointMapping;
import org.springframework.ws.server.endpoint.support.PayloadRootUtils;

@Component
public class LocalPartAnnotationMethodEndpointMapping extends AbstractAnnotationMethodEndpointMapping<String> {

	private static TransformerFactory transformerFactory;

	static {
		transformerFactory = TransformerFactory.newInstance();
	}

	@Override
	protected String getLookupKeyForMessage(MessageContext messageContext) throws Exception {
		QName qname = PayloadRootUtils.getPayloadRootQName(messageContext.getRequest().getPayloadSource(), transformerFactory);
		if (qname == null) {
			return null;
		}
		return qname.getLocalPart();
	}

	@Override
	protected String getLookupKeyForMethod(Method method) {
		PayloadRoot annotation = method.getAnnotation(PayloadRoot.class);
		if (annotation != null) {
			QName qname;
			if (StringUtils.hasLength(annotation.localPart()) && StringUtils.hasLength(annotation.namespace())) {
				qname = new QName(annotation.namespace(), annotation.localPart());
				return qname == null ? null : qname.getLocalPart();
			} else {
				qname = new QName(annotation.localPart());
				return qname == null ? null : qname.getLocalPart();
			}
		} else {
			return null;
		}
	}

	@Override
	protected void initApplicationContext() throws BeansException {
		super.initApplicationContext();
		this.setOrder(-1);
	}

}
