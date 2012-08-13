Javascript Remoting, AJAX and JSON Using Play 2.0
=================================================

Prerequisites
------------

You will need to know a little  java, javascript and how play 2.0 works. 
There are comments in the code that should help. You will need play 2.0 and git 
installed to run this example from your own machine.

To run it, simply do a "git clone", cd to the project directory, then "play run".

The Code
--------

It's a pretty simple play 2.0 app. Start by looking at [Application.java](/aogriffiths/play2.0-ajax-examples/blob/master/app/controllers/Application.java) 
which defines four methods:

* sayHello()
* sayHelloToString(String name)
* sayHelloToJson()
* sayHelloWithJson(String name)

Which are made available to be be called from client side javascript by the last method:

* javascriptRoutes()

All five of these java methods have http methods routed to them by the URLs defined in the
standard play [routes](/aogriffiths/play2.0-ajax-examples/blob/master/conf/routes) file (four are GET methods and one is a POST method).

They are called from the client side "index page" defined by 
[index.scala.html](/aogriffiths/play2.0-ajax-examples/blob/master/conf/app/views/index.scala.html). This file contains most of the javascript responsible for
calling the methods above.

* sayHello is called as a simple GET request. 
* sayHelloToString is called as a GET request with the name parameter in the query string.
* sayHelloToJson is called as POST request with the parameters posted as JSON in the 
request body. The response is sent back as JSON
* sayHelloWithJson is called as a GET request and the responses is JSON.

For all of these methods to work seamlessly from the client side there one important 
piece of glue, the "jsRoutes". Basically the best way to understand this is to first 
read the java method:

    app.controllers.Application.javascriptRoutes()

Which builds a javascript "routing" script, which is itself is routed to, with (defined
in the conf/routes file): 

    GET     /assets/javascripts/routes  controllers.Application.javascriptRoutes()
 
And called into the browser by the line (defined in the app/views/main.scala.html file):

    <script type="text/javascript" src="@routes.Application.javascriptRoutes"></script>

This means javascript in app/views/index.scala.html can easily call the java in 
app.controllers.Application with lines like 

    jsRoutes.controllers.Application.sayHello()


That's the highlights, hopefully you can get all details and nuances of the four methods from
the code. Applications.java and index.scala.html both contains comments. If you would like to 
understand more of what is going on under the hood there are comments in Applications.java
on how to use cURL to play with the examples and index.scala.html includes debug statements 
for your browser's javascript debug console. 
