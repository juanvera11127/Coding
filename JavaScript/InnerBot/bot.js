var fs = require('fs');
var Twitter = require('twitter');

console.log("setting up...");

var client = new Twitter( {
	consumer_key:			'KuRmMvZbVmqhkG5v5sEQO2UIG'
	,consumer_secret:		'YmEA5GyQRUXQZZvvw0EgdNXbY3hHnjKT5fKnS56XRtKMmBgcHo'
	,access_token_key:		'1205851912735584257-F3AdrWetDLMnbU27XVNtHKGtJ7l6GS'
	,access_token_secret:		'EXlKajVlZLJQO8gu3TSraP56TL41S5G4GyJGojKecJm6t'
}); 

var file = 'new.txt';
var str = fs.readFileSync(file).toString();
var buffer = "";
var title = "";
var arr = [];
var set = new Set();
var TITLE_CHAR = '$';
var ALBUM_CHAR = ':';
var LYRIC_CHAR = '*';
var TIME = 3600000;
var d = new Date();

for(var i = 0; i < str.length; i++) {
	if(str.charAt(i) == TITLE_CHAR) {
		i++;
		title = "";
		while(str.charAt(i) != TITLE_CHAR) {
			title += str.charAt(i);
			i++;
		}
	}
	if(str.charAt(i) == LYRIC_CHAR && i+2 < str.length) {
		buffer = "";
		i++;
		while(str.charAt(i) != LYRIC_CHAR && i+2 < str.length) {
			buffer += str.charAt(i);
			i++;
		}
		if(str.charAt(i+3) != TITLE_CHAR) {
			i--;
		}
		buffer.trim();
		if(buffer.length > 8) {
			buffer = buffer.substring(2,buffer.length-2);
			buffer = title + TITLE_CHAR + buffer;
			arr.push(buffer);
		}
		
	}
}

console.log("Setup complete!");
timer();
setInterval(timer, TIME);

function timer() {
	var rand = Math.floor(Math.random() * arr.length);
	while(set.has(rand)) {
		rand = Math.floor(Math.random() * arr.length);
	}
	
	var str = arr[rand];
	for(var i = 0; i < str.length; i++) {
		if(str.charAt(i) == TITLE_CHAR) {
			var title = str.substring(0, i);
			str = str.substring(i+1);
			break;
		}
	}
	for(var i = 0; i < title.length; i++) {
		if(title.charAt(i) == ALBUM_CHAR) {
			var album = title.substring(0,i);
			title = title.substring(i+1);
			break;
		}
	}
	
	
	client.post('statuses/update', {status: str},  function(error, tweet, response) {
	
		if(error) {
 			console.log(error);
			setInterval(timer, TIME);
		}
		else {
			d = new Date();
			console.log("Tweeted! at "  + d.getHours() + ":" + d.getMinutes() + ":" + d.getSeconds());
			setTimeout(function(){
				client.post('statuses/update', {
						status: "@" + tweet.user.screen_name + " " + "Album: " + album + "\nTrack: " + title, 
						in_reply_to_status_id: tweet.id_str, 
						auto_populate_reply_metadata: false}, 
						function(err, tw, re) {
					if(err) console.log(err);
					else {
						d = new Date();
						console.log("Commented! at "  + d.getHours() + ":" + d.getMinutes() + ":" + d.getSeconds());
					}
				});
			}, 4000);
		}
	});   
	set.add(rand);
	if(set.size == arr.length) {
		set.clear();
	}
}