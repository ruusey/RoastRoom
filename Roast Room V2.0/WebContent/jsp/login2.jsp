<!DOCTYPE html>
<!--[if lt IE 7]> <html class="lt-ie9 lt-ie8 lt-ie7" lang="en"> <![endif]-->
<!--[if IE 7]> <html class="lt-ie9 lt-ie8" lang="en"> <![endif]-->
<!--[if IE 8]> <html class="lt-ie9" lang="en"> <![endif]-->
<!--[if gt IE 8]><!-->
<html lang="en">
<!--<![endif]-->
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>Images Login Form</title>
<link rel="stylesheet" type="text/css"
	href="/ImagesProxy/jsp/styleJSP.css">
<link href="icon.jpg" rel="icon" type="image/x-icon" />
<link href="icon.jpg" rel="shortcut icon" type="image/x-icon" />
<!--[if lt IE 9]><script src="//html5shim.googlecode.com/svn/trunk/html5.js"></script><![endif]-->
</head>

<body>
	<div class="login">
		<h1>Login to Images</h1>
		<form method="POST"
			action='<%= response.encodeURL("j_security_check") %>'>
			<p>
				<input type="text" name="j_username" value="" placeholder="Username">
			</p>
			<p>
				<input type="password" name="j_password" value=""
					placeholder="Password">
			</p>
			<p class="remember_me">
				<label> <input type="checkbox" name="remember_me"
					id="remember_me"> Remember me on this computer
				</label>
			</p>
			<p class="submit">
				<input type="submit" name="commit" value="Log In">
			</p>
			<p>
				Not Registered? <a href="NewUser.html">Click here to Register</a>.
			</p>
		</form>
	</div>

</body>
</html>