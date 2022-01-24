<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
		<html>

		<head>
			<meta charset="UTF-8">
			<meta name="viewport" content="width=device-width, initial-scale=1">
			<title>Pokédex</title>
			<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
				integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
				crossorigin="anonymous">
			<style>
				html,
				body {
					height: 100%;
				}

				body {
					display: -ms-flexbox;
					display: -webkit-box;
					display: flex;
					-ms-flex-align: center;
					-ms-flex-pack: center;
					-webkit-box-align: center;
					align-items: center;
					-webkit-box-pack: center;
					justify-content: center;
					padding-top: 40px;
					padding-bottom: 40px;
					background-color: #f5f5f5;
				}

				.form-signin {
					width: 100%;
					max-width: 330px;
					padding: 15px;
					margin: 0 auto;
				}

				.form-signin .checkbox {
					font-weight: 400;
				}

				.form-signin .form-control {
					position: relative;
					box-sizing: border-box;
					height: auto;
					padding: 10px;
					font-size: 16px;
				}

				.form-signin .form-control:focus {
					z-index: 2;
				}

				.form-signin input[type="text"] {
					margin-bottom: -1px;
					border-bottom-right-radius: 0;
					border-bottom-left-radius: 0;
				}

				.form-signin input[type="password"] {
					margin-bottom: 10px;
					border-top-left-radius: 0;
					border-top-right-radius: 0;
				}
			</style>
		</head>

		<body class="text-center">
			<form:form id="submitForm" class="form-signin" name="submitForm" action="/login" method="POST">
				<h1 class="h3 mb-3 font-weight-normal">Please sign in</h1>
				<label for="inputEmail" class="sr-only">Email address</label>
				<input type="text" name="userName" id="inputEmail" class="form-control" placeholder="Username"
					required="" autofocus="">

				<label for="inputPassword" class="sr-only">Password</label>
				<input type="password" name="password" id="inputPassword" class="form-control" placeholder="Password"
					required="">
				<button class="btn btn-lg btn-primary btn-block" type="submit" value="Submit">Sign in</button>
				<div style="color: red">${error}</div>
			</form:form>

		</body>

		</html>
