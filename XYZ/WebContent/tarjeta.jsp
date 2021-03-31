<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="MVC.beans.*, java.util.*, java.text.*"%>
    <%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%
	response.setHeader("Cache-Control","no-cache");
	response.setHeader("Pragma","no-cache");
	response.setDateHeader ("Expires", -1);
	response.setHeader("Cache-Control","no-store"); //HTTP 1.1
	
	Cliente usuario = (Cliente)session.getAttribute("usuario");

	
%>
<%-- Verify that the user is logged in --%>
<c:if test="${usuario == null}">
	<jsp:forward page="login.html"></jsp:forward>
</c:if>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8">
    <title>XYZ | Menu</title>
    <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
    <!-- Bootstrap 3.3.2 -->
     <link href="./css/bootstrap.min.css" rel="stylesheet" type="text/css" />
    <!-- Font Awesome Icons -->
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
    <!-- Theme style -->
    <link href="./css/AdminLTE.min.css" rel="stylesheet" type="text/css" />
    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
    <![endif]-->
    <script src="./js/jquery.min.js"></script>
    <script type="text/javascript">
    
    function guardar()
    {
    	if(document.getElementById("numero").value.length != 16 || document.getElementById("codigo").value.length<3 || document.getElementById("codigo").value.length>4 || document.getElementById("mesExp").value == 0 || document.getElementById("anoExp").value == 0 || document.getElementById("titular").value == ""  || document.getElementById("titular").value.length > 20)
    	{
    	alert("Nombre de titular vacio o mayor a 20 caracteres.\nEl número debe ser de la tarjeta es de 16 caracteres.\nEl código de seguridad debe ser de 3 o 4 digitos.\nSelecciona el mes y año de expiración de la tarjeta.");
    	
    	}else{
    	document.getElementById('accion').value = "addCard";
    	document.forms[0].action = "control";
    	document.forms[0].submit();
    	}
    }
    
   
    
    function salir()
    {
    	document.getElementById('accion').value = "logout";
    	document.forms[0].action = "control";
    	document.forms[0].submit();
    }
    
    function ocultarMensaje(){
    	var options = {};
    	$("#contenedor_mensaje").hide('blind',options,500);		        
        clearTimeout(timerID);
    }
    </script>
  </head>
  <body>
 <header class="main-header">
        <!-- Header Navbar: style can be found in header.less -->
        <nav class="navbar navbar-static-top" role="navigation">
         
          <div class="navbar-custom-menu">
            <ul class="nav navbar-nav">

              <!-- User Account: style can be found in dropdown.less -->
              <li class="dropdown user user-menu">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                  
                  <span class="hidden-xs"><%=usuario.getNombre() %></span>
                </a>
                <ul class="dropdown-menu">
                  <!-- User image -->
                  <!-- Menu Footer-->
                  <li class="user-footer">
                    <div class="pull-left">
                      <a href="perfil.jsp" class="btn btn-default btn-flat">Perfil</a>
                    </div>
                    <div class="pull-right">
                      <a href="javascript:salir()" class="btn btn-default btn-flat">Salir</a>
                    </div>
                  </li>
                </ul>
              </li>
            </ul>
          </div>
        </nav>
      </header>  
      
      <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
          <h1>
            Tarjeta
          </h1>
          <ol class="breadcrumb">
            <li><a href="#"><i class="fa fa-dashboard"></i> Tarjeta</a></li>
            <li class="active">Nueva</li>
          </ol>
        </section>

        <!-- Main content -->
        <section class="content">
        <div class="row">
        <div class="col-lg-6 col-xs-6">
        <h5>Tarjeta <i class="fa fa-credit-card"></i> <a href="PrincipalMenu.jsp">Volver <i class="fa fa-undo"></i></a></h5>
      	 <a href="javascript:guardar()">Guardar <i class="fa fa-save"></i></a>
        <form action="./control" method="post">
        <input type="hidden" value="" id="accion" name="accion">
        <fieldset>
        <label> <i class="fa fa-info"></i> Número:</label>
        <input type="text" name="numero" class="form-control" id="numero">
        </fieldset>
        <fieldset>
        <label><i class="fa fa-user"></i> Titular:</label>
        <input type="text" name="titular" class="form-control" id="titular">
        </fieldset>
        <fieldset>
        <label><i class="fa fa-university"></i> Emisor:</label>
        <select name="emisor" class="form-control" id="emisor">
        <option value="Visa">Visa</option>
        <option value="MasterCard">MasterCard</option>
        <option value="American Express">American Express</option>
        </select>
        </fieldset>
        <fieldset>
        <label><i class="fa fa-calendar"></i> Expira en:</label><br>
        <select name="mesExp" id="mesExp" class="form-control" style="width:50%;display:inline">
								<option value="0">Mes</option>
								<option value="01">01</option>
								<option value="02">02</option>
								<option value="03">03</option>
								<option value="04">04</option>
								<option value="05">05</option>
								<option value="06">06</option>
								<option value="07">07</option>
								<option value="08">08</option>
								<option value="09">09</option>
								<option value="10">10</option>
								<option value="11">11</option>
								<option value="12">12</option>
							
							</select><select name="anoExp" id="anoExp" class="form-control" style="width:50%;display:inline">
								<option value="0">Año</option>
								<%
									for(int i=2022;i<2040;i++){%>
							
							<option value="<%=(i<10)? "0"+i : i%>"><%=(i<10)? "0"+i : i%></option>
								
							
							<%} %>				
							</select></fieldset>
        <fieldset>
        <label><i class="fa fa-money-check"></i> Código CVC o CVV:</label>
        <input type="text" name="codigo" class="form-control" id="codigo">
        </fieldset>
        
        
        </form>
        <a href="javascript:guardar()">Guardar <i class="fa fa-save"></i></a>
        </div>
      
        </div>
        </section>
        </div>
    <script src="./js/jquery-ui.min.js"></script>
    <!-- Bootstrap 3.3.2 JS -->
    <script src="./js/bootstrap.min.js" type="text/javascript"></script>
  </body>
</html>