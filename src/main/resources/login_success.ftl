<#-- @ftlvariable name="" type="com.kainos.training.dropwizard.login.frontends.views.LoginSuccessView" -->
<main id="content" role="main" xmlns="http://www.w3.org/1999/html">
<h1 id="login-success-message">BookFace</h1>

<h1>Add a new friend</h1>
	<form method="POST" action="/test/friend-details">
	<input name="friendName" placeholder="Friend's name"></input>
	<input id="submit" type="submit" value="Submit">
</form>

<ul>
	<#if friends??>
			<#list friends as friend>
			    <li>
			    	${friend.getName()}
			    	<a href="/test/friend-delete/${friend.getId()}">DELETE THE BASTARD</a>
			    </li> 
			</#list>
	<#else>
			<li>Go and get some friends....</li>
	</#if>
</ul>

</main>


