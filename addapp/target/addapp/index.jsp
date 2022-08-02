<html>

<body>
    <h1>Calculation Web application!!!</h1>
    <form method="get">
        Enter the first number <input type="text" name="firstNumber"
            value='<%if(session.getAttribute("firstNumber")!=null){ out.println(session.getAttribute("firstNumber"));} else{out.println("");}%>'><br>
        Enter the second number <input type="text" name="secondNumber"
            value='<%if(session.getAttribute("secondNumber")!=null){ out.println(session.getAttribute("secondNumber"));} else{out.println("");}%>'><br>
        <input type="submit" value="+" onclick="form.action='addserv';">
        <input type="submit" value="-" onclick="form.action='subserv';">
        <input type="submit" value="*" onclick="form.action='mulserv';">
        <input type="submit" value="/" onclick="form.action='divserv';">
    </form>
    <% 
    if(session.getAttribute("sum")!=null){ 
        out.println("The sum of the given numbers is: "+session.getAttribute("sum"));
        session.setAttribute("sum",null);
    }
    if(session.getAttribute("diff")!=null){
        out.println("The difference of the given numbers is: "+session.getAttribute("diff"));
        session.setAttribute("diff",null);
    }
    if(session.getAttribute("mul")!=null){ 
        out.println("The multiplication of the given numbers is: "+session.getAttribute("mul"));
        session.setAttribute("mul",null);
    }
    if(session.getAttribute("div")!=null){
        out.println("The division of the given numbers is: "+session.getAttribute("div"));
        session.setAttribute("div",null);
    }
    %>
</body>

</html>