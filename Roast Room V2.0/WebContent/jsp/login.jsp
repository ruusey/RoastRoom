
<html>
<head>

<link rel="stylesheet" type="text/css"
	href="/9GagProxy/jsp/styleJSP.css">
<title>Login Page for Examples</title>
</head>
<body>
	<form method="POST"
		action='<%= response.encodeURL("j_security_check") %>'>
		<table border="0" cellspacing="5">
			<tr>
				<th align="right">Username:</th>
				<td align="left"><input type="text" name="j_username"></td>
			</tr>
			<tr>
				<th align="right">Password:</th>
				<td align="left"><input type="password" name="j_password"></td>
			</tr>
			<tr>
				<td align="right"><input type="submit" value="Log In"></td>
				<td align="left"><input type="reset"></td>

			</tr>

		</table>
		<p>
			Not Registered? <a href="NewUser.html">Click Here To Register</a>.
		</p>
	</form>
</body>
</html>
