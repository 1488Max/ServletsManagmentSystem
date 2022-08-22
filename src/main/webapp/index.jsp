<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Add dev</title>
</head>
<body>

<h1>Add New Developer</h1>
<form action="addDeveloper" method="post">
    <table>
        <tr><td>Name:</td><td><input type="text" name="name"/></td></tr>
        <tr><td>Sex:</td><td><input type="text" name="sex"/></td></tr>
        <tr><td>Salary:</td><td><input type="number" name="salary"/></td></tr>
        <tr><td colspan="2"><input type="submit" value="addDeveloper"/></td></tr>
    </table>
</form>

<br/>

<br/>
<a href="ViewDev">View developers</a><br>

<a href="AddCustomer">Add customers</a>
</body>
</html>