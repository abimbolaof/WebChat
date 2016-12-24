
onload = function(){
    var mbox = document.getElementById("send-message-box")
    mbox.addEventListener("keypress", function(e){
        if (e.which == 13){
                sendMessage();
        }
    });
};
    var socket;
    var username = "";

            function loginUser() {
                if (window.WebSocket){
                    socket = new WebSocket("ws://localhost:8080/webchat/chat");
                    socket.onopen = handleOpen;
                    socket.onmessage = handleMessage;
                    socket.onclose = handleClose;
                    socket.onerror = handleError;
                }else{
                    alert("Hey!!! It seems my code is not compatible with your browser type yet.\n" +
                         "I'm working on it!!! :)");
                }
            };

            function logoutUser(){
                socket.close();
                var but = document.getElementById("logoutButton");
                but.setAttribute("class", "loghide");
                
                var but2 = document.getElementById("loginButton");
                but2.setAttribute("class", "");
            };

            function handleOpen(evt){
                username = prompt("Please enter your name");

                if (username){
                    var msg = {
                        from : username,
                        message : "",
                        lggin : "lggin"
                    };
                    socket.send(JSON.stringify(msg));
                    
                    var but = document.getElementById("loginButton");
                    but.setAttribute("class", "loghide");
                    var but2 = document.getElementById("logoutButton");
                    but2.setAttribute("class", "");

                    var info = document.getElementById("userinfo");
                    info.innerHTML = "Logged in as: " + msg.from;

                }
            };

            function handleMessage(event){
                var m = JSON.parse(event.data);
                
                var msgbox = document.getElementById("message-box");
                var text = m.from + " --- " + m.message + "\n";
                msgbox.innerHTML += text;
                /*var p = document.createElement("P");
                var text = document.createTextNode(m.from + " --- " + m.message);
                p.appendChild(text);
                msgbox.appendChild(p);*/
            };

            function handleClose(event){
                var info = document.getElementById("userinfo");
                info.innerHTML = "";
                
                var but = document.getElementById("logoutButton");
                but.setAttribute("class", "loghide");
                
                var but2 = document.getElementById("loginButton");
                but2.setAttribute("class", "");
                
                alert("You have been logged out");
            };

            function sendMessage(){
                //alert("");
                var box = document.getElementById("send-message-box");
                var message = box.value;
                var msg = {
                        from : username,
                        message : message,
                        lggin : ""
                };
                socket.send(JSON.stringify(msg));
                box.value = "";
            };