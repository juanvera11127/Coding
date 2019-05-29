
var name = prompt("Enter a name");
var list = [0,1,2,3,4,5,6,7,8,9];
var sub = 0;
var a, ans, boo, total = 0, time = 0, count = 0, count2 = 0;
var notFinished = true;
var headline = document.getElementById("click");
var length = list.length, listnum = 0;

alert("This is a math game where you'll get 10 math questions and a score based on how fast you answer them and how many you got correct, press OK.");

function problem(n) { 

  if(n == 0) {
    return ("What's 20 x 30?");
  }  if(n == 1) {
    return ("What's 23 x 3?");
  }  if(n == 2) {
    return ("What's 100 / 20?");
  }  if(n == 3) {
    return ("y = x+5, what does x equal?");
  }  if(n == 4) {
    return ("How many feet in a mile");
  }  if(n == 5) {
    return ("What's the area of a square with a length of 5");
  }  if(n == 6) {
    return ("4+7");
  }  if(n == 7) {
    return ("What's 10 x 30?");
  }  if(n == 8) {
    return ("What's 30 x 30?");
  }  if(n == 9) {
    return ("What's 40 x 30?");
  }
	if(n == 10) {
	return ("What's squareroot of 25");
	}
	
	if(n == -1) {
	notFinished = false;
	return ("Done");
	}
	
};


function check(c,d) {

	  
  if(c==0) {
    return(d=="600");
  }  if(c==1) {
    return(d=="69");
  }  if(c==2) {
    return(d=="5");
  }  if(c==3) {
    return(d=="y-5");
  }  if(c==4) {
    return(d=="5280");
  }  if(c==5) {
    return(d=="25");
  }  if(c==6) {
    return(d=="11");
  }  if(c==7) {
    return(d=="300");
  }  if(c==8) {
    return(d=="900");
  }  if(c==9) {
    return(d=="1200");
  }
	if(c==10) {
		return(d=="5");
	}
  
};

function getRandomInt(min, max) {
  min = Math.ceil(min);
  max = Math.floor(max);
  return Math.floor(Math.random() * (max - min)) + min; //The maximum is exclusive and the minimum is inclusive
}

function myFunction() {
	document.getElementById("ans").disabled = false;
	document.getElementById("press").disabled = true;
	if(notFinished) {
		time++;
	}
    document.getElementById('click').innerHTML = "time: " + time
	setTimeout(myFunction, 1000);
	if(count == 0) {
		
		myFunction2();

	}
}
function myFunction2() {
	if(count > 0) {
		boo = check(a,document.getElementById("textBox").value);
		list.splice(list.indexOf(a),1);
		if(boo) {
			total++;
		}
		document.getElementById("check").innerHTML = (boo ? "Correct!" : "Wrong :(");
		a = getRandomInt(0,length);

		while(list.indexOf(a) == -1) {
			if(list.length<1) {
				notFinished = false;
				break;
			}
			a = getRandomInt(0,length);
		}
		document.getElementById("test").innerHTML = "a = " + a + " count = " + count + " list = "+ list;

		count++;
		document.getElementById("input").innerHTML = problem(a);
		document.getElementById("textBox").value = "";		
	}
	else {

		count++;
		document.getElementById("input").innerHTML = problem(10);
		a = 10;
	}
	if(!notFinished) {
		prompt("Good job " + name + "!  Your score is: " + Math.round(Math.pow(10,total) / (100*time)));
	}
}






