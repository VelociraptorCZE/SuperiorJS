/*
* SuperiorJS v1.0
* Copyright (C) Simon Raichl 2018
* MIT License
* Use this as you want, share it as you want, do basically whatever you want with this :)
*/

let write = (param) => {
    console.log(param);
    document.getElementById("out").innerHTML += param + "\n";
};

let fetchQuestions = () => {
    fetch("https://raw.githubusercontent.com/VelociraptorCZE/SuperiorJS/master/app/src/main/assets/questions")
    .then((raw) => { return raw.text() })
    .then((string) => {
        parseAndExecute(string);
    });
};

let parseAndExecute = (code) => {
    code = code.replace(/(::true)|(::false)/g, "").split("\n");
    code.forEach((param) => {
       write(param + " returns " + new Function("return " + param)());
    });
};

fetchQuestions();