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
    function deleteCard(num,id)
    {
    	
		var flag = confirm("¿Está seguro de eliminar la tarjeta número ****-****-"+num+" ?");
    	
    	if(flag)
    		{
    		document.getElementById('accion').value = "deleteCard";
    		document.getElementById('tarjeta').value = id;
        	document.forms[0].action = "control";
        	document.forms[0].submit();
    		}
    }
    
    function defaultCard(num,id)
    {
	var flag = confirm("La tarjeta número ****-****-"+num+" será la tarjeta predeterminada para pagos.\n¿Está seguro?");
    	
    	if(flag)
    		{
    		document.getElementById('accion').value = "defaultCard";
    		document.getElementById('tarjeta').value = id;
        	document.forms[0].action = "control";
        	document.forms[0].submit();
    		}
    }
    
    function salir(){
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
            Menu
          </h1>
          <ol class="breadcrumb">
            <li><a href="#"><i class="fa fa-dashboard"></i> Inicio</a></li>
            <li class="active">Resumen</li>
          </ol>
        </section>
<script type="text/javascript">
				exito = false;
			</script>
			<%				
				MensajeSistema mensaje = (MensajeSistema) session.getAttribute("mensaje");
				if(mensaje != null){					
				
					if(mensaje.getTipo().equals("Exito")){
					
			%>
			
			<div id="contenedor_mensaje" >
				<div id="cuerpo_mensaje" >
					<p>
						
						<%= mensaje.getMensaje() %>
					</p>
				</div>
			</div>
			<script type="text/javascript">
				exito = true;
			</script>
			
			<%
					
					}else if (mensaje.getTipo().equals("Error")){
			
			%>
			
			<div id="contenedor_mensaje" >
				<div id="cuerpo_mensaje" >
					<p>
						
						<%= mensaje.getMensaje() %>
					</p>
				</div>
			</div>			
			
			<%	
					}
					
			%>
			
			<script type="text/javascript">				 
				timerID = setTimeout("ocultarMensaje()",8000);
			</script>
			
			<%				
					session.removeAttribute("mensaje");	
			
				}
			%>
        <!-- Main content -->
        <section class="content">
        <div class="row">
        <div class="col-lg-6 col-xs-6">
        <h5>Perfil <i class="fa fa-user"></i></h5>
        <a href="perfil.jsp">Editar <i class="fa fa-edit"></i></a>
        <form action="./control" method="post">
        <input type="hidden" value="" id="accion" name="accion">
         <input type="hidden" value="" id="tarjeta" name="tarjeta">
        <fieldset>
        <label> <i class="fa fa-envelope"></i> Correo:</label>
        <input type="text" name="mail" value="<%=usuario.getEmail() %>" class="form-control" disabled>
        </fieldset>
        <fieldset>
        <label><i class="fa fa-info-circle"></i> Nombres:</label>
        <input type="text" name="nombre" value="<%=usuario.getNombre() %>" class="form-control" disabled>
        </fieldset>
        <fieldset>
        <label><i class="fa fa-tag"></i> Tipo:</label>
        <select name="regimen" class="form-control" disabled>
        <%if(usuario.getRegimen()==0){%>
        <option value="0" selected>Persona Natural</option>
        <option value="1">Persona Jurídica</option>
        <%}else{ %>
        <option value="0">Persona Natural</option>
        <option value="1" selected>Persona Jurídica</option>
        
        <%} %>
        </select>
        </fieldset>
        <fieldset>
        <label> NIT:</label>
        <input type="text" name="nit" value="<%=usuario.getNit() %>" class="form-control" disabled>
        </fieldset>
        <fieldset>
        <label><i class="fa fa-phone"></i> Teléfono:</label>
        <input type="text" name="telefono" value="<%=usuario.getTelefono() %>" class="form-control" disabled>
        </fieldset>
        <fieldset>
        <label><i class="fa fa-map-marker"></i> Dirección:</label>
        <input type="text" name="direccion" value="<%=usuario.getDireccion() %>" class="form-control" disabled>
        </fieldset>
        
        
        </form>
        </div>
        <div class="col-lg-6 col-xs-6">
        <h5>Tarjetas <i class="fa fa-credit-card"></i></h5>
        <div> <a href="tarjeta.jsp">Nueva <i class="fa fa-plus-square"></i></a></div>
        <div class="table-responsive">
        
			    <table class="table">
			    <thead>
			    <tr>
			    <th>Número</th>
			    <th>Titular</th>
			    <th>Emisor</th>
			    <th>Expira</th>
			    <th>Predeterminada</th>
			    <th><i class="fa fa-cog"></i></th>
			    </tr></thead>
			    <tbody>
			    <%if(usuario.getTarjetas()==null || usuario.getTarjetas().size()==0){ %>
			    <tr>
			    <td colspan="6">No tienes tarjetas registradas.</td>
			    </tr>
			    <%}else{
			    for(Tarjeta t: usuario.getTarjetas()){
			    %>
			    <tr>
			    <td>****-****-<%=t.getNumero().substring(12)%></td>
			    <td><%=t.getTitular() %></td>
			    <td><%=t.getMarca() %></td>
			    <td><%=t.getExpira() %></td>
			    <%if(t.getDefecto()==0){ %>
			    <td><a href="javascript:defaultCard(<%=t.getNumero().substring(12)%>,<%=t.getId()%>)">Establecer</a></td>
			    <%}else{ %>
			    <td><i class="fa fa-check"></i></td>
			    <% }%>
			    <td><a href="javascript:deleteCard(<%=t.getNumero().substring(12)%>,<%=t.getId()%>)"><i class="fa fa-trash"></i></a></td>
			    
			    </tr>
			    <%}
			    } %>
			    </tbody>
			    </table>
		</div>
        </div>
        </div>
        </section>
        </div>
  <script src="./js/jquery-ui.min.js"></script>
    <!-- Bootstrap 3.3.2 JS -->
    <script src="./js/bootstrap.min.js" type="text/javascript"></script>
  </body>
</html>