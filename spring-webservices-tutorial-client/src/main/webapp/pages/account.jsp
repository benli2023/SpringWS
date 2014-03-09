<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>


<table class="formTable">
			<tr>	
				<td class="tdLabel">AccountNumber:</td>	
				<td><c:out value='${account.accountNumber}'/></td>
			</tr>
			<tr>	
				<td class="tdLabel">AccountName:</td>	
				<td><c:out value='${account.accountName}'/></td>
			</tr>
			<tr>	
				<td class="tdLabel">AccountBalance:</td>	
				<td><c:out value='${account.accountBalance}'/></td>
			</tr>
			<tr>	
				<td class="tdLabel">AccountStatus:</td>	
				<td><c:out value='${account.accountStatus}'/></td>
			</tr>
			<tr>	
				<td class="tdLabel">SortCode:</td>	
				<td><c:out value='${account.sortCode}'/></td>
			</tr>
			
</table>
