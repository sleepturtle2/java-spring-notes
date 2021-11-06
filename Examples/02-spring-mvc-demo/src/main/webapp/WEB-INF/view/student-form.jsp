<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>

<html>
	<head>
		<title>
			Student Registration Form
		</title>
	</head>
	<body>
		<form:form action="processForm" modelAttribute="student">
				First Name: <form:input path="firstName"/>
				<br>
				Last Name: <form:input path="lastName"/>
				<br>
				
				Country: 
				<form:select path="country">
					<!-- HardCoded options Syntax: 
					<form:option value="Brazil" label="Brazil"/>
					 -->
					 <form:options items="${student.countryOptions }" />
					
					
				</form:select>
				
				<br>
				
				Favorite Language: 
				
				Java <form:radiobutton path="favoriteLanguage" value="Java"/>
				Ruby <form:radiobutton path="favoriteLanguage" value="Ruby"/>
				C# <form:radiobutton path="favoriteLanguage" value="C#"/>
				PHP <form:radiobutton path="favoriteLanguage" value="PHP"/>
				
				<br>
		
		Operating Systems: 
		
		Linux<form:checkbox path="operatingSystems" value="Linux"/>
		Mac OS<form:checkbox path="operatingSystems" value="Mac OS"/>
		MS Windows<form:checkbox path="operatingSystems" value="MS Windows"/>
			<input type="submit" value="Submit"/>
		</form:form>
		
	</body>
</html>
<!-- When the form is run, 
	Spring MVC will call : student.getFirstName(
 and student.getlastName -->
 
 <!-- When the 
 form submits, calls : student.
 setFirstName() and setLastNamw()-->