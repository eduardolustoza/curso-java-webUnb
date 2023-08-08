<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<html>
<head>
    <title>Calculadora</title>
  <!-- Outras meta tags, títulos, etc... -->
  <link href="https://fonts.googleapis.com/css?family=Roboto&display=swap" rel="stylesheet">
   <link rel="stylesheet" type="text/css" href="css/estilo.css">
</head>
<body>

    <h1>Calculadora</h1>

    <html:form action="/calculadora" method="post">

        <html:hidden property="method" value="${'calcular'}"/>
		
		<span style="color: red">
        <html:errors/>
		</span>
        <p>
            <label for="expressao">Expressão:</label>
            <html:text property="expressao" size="50"/>
        </p>

        <p>
            <html:submit value="Calcular"/>
        </p>

    </html:form>

    <h2>Resultado</h2>
        <p>
<!--             <label for="resultado">Resultado:</label> -->
            <bean:write name="calcForm" property="resultado" />
        </p>

</body>
</html>
