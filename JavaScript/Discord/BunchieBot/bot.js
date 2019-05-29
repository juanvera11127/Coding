var Discord = require('discord.io');
var logger = require('winston');
var auth = require('./auth.json');
var XMLHttpRequest = require("xmlhttprequest").XMLHttpRequest;
var Twitter = require('twitter');
//var Gpio = require('onoff').Gpio; //include onoff to interact with the GPIO
//var Button = new Gpio(4, 'in', 'both', {debounceTimeout: 10});
var inGame = false;
var ID = 0;
var Board;
var p1;
var p2;
var tttcount;
var turn = 1;

/*button.watch((err, value) => {
	if(err)
		throw err
	if(value == 1)
		console.log("button press");
		bot.sendMessage({
            to: ID,
            message: "!play bruh sound effect #2"
        });
}); */

var client = new Twitter( {
	consumer_key:			'531hAGw25FFa9p2URKgKJMOqh'
	,consumer_secret:		'ycVlGvVFPwUsSPdIMPbD7ooPm0iZHdEsO4tUFwljHBHghsE98N'
	,access_token_key:		'842110915428343808-hDTIT8b9w2cozefUKP9xkXArmMnGdMk'
	,access_token_secret:	'yUBx1g8LDquKmqze9xux7rxfHjnWyrHmYdxkKDxv3Qhtw'
});

// Configure logger settings
logger.remove(logger.transports.Console);
logger.add(new logger.transports.Console, {
    colorize: true
});
logger.level = 'debug';
// Initialize Discord Bot
var bot = new Discord.Client({
   token: auth.token,
   autorun: true
});
bot.on('ready', function (evt) {
    logger.info('Connected');
});
bot.on('message', function (user, userID, channelID, message, evt) {
	ID = channelID;
	var args = message.split(' ');
    if (message.substring(0, 1) == '!' && user != "BunchieBot") {
        args = message.substring(1).split(' ');
		var cmd = args[0];
        args = args.splice(1);
        switch(cmd) {
            
			case 'joinme':
				var channel = message.member.voiceChannel;
				channel.join().then(connection => console.log('Connected!'))
    .catch(console.error);
				break;
			
            case 'fact':
				var ourRequest = new XMLHttpRequest();
				var toSend;
				ourRequest.open('GET', 'http://api.icndb.com/jokes/random?firstName=Bunchie&amp;lastName=Vera'); // false for synchronous request
				ourRequest.onload = function() {
					var ourData = JSON.parse(ourRequest.responseText);
					toSend = ourData.value.joke;
					bot.sendMessage({
                    to: channelID,
                    message: toSend
                });
				};
				ourRequest.send();
				break;
	        
			case 'cat':
				var ourRequest = new XMLHttpRequest();
				var pic;
				ourRequest.open('GET', 'https://api.thecatapi.com/v1/images/search?', true);
				ourRequest.onload = function() {
					var ourData = JSON.parse(ourRequest.responseText);
					pic = ourData[0].url;
					bot.sendMessage({
						to: channelID,
						message: "Here's a cat pic.",
						embed: { 'image': { 'url': pic}
						}
					});				
				};
				ourRequest.send();
				break;
			
			case 'uwu':
				var toReturn = "";
				var word = "";
				
				for(var i = 0; i < args.length; i++) {
					word = args[i].toLowerCase();
					switch(word) {
						case 'hello':
						word = 'hewwo';
						break;
						case 'you' || 'u':
						word = 'woo';
						break;
					}
					for(var j = 0; j < word.length; j++) {
						if(j+1 < word.length) {	
							if(word.charAt(j) == "r"|| word.charAt(j) == "l") {
								if(j == 0 && isVowel(word.charAt(j+1)) || !isVowel(word.charAt(j-1)) && isVowel(word.charAt(j+1))) {
									word = word.substring(0, j) + "w" + word.substring(j+1);
								}
							}
							if(word.charAt(j) == "m" && isVowel(word.charAt(j+1))) {
								word = word.substring(0,j+1) + "w" + word.substring(j+1);
								j++;	
							}
						}
					}			
					word = word.toUpperCase();
					toReturn += " " + word;
				}
				toReturn += " ~UWU";
				bot.sendMessage({
                    to: channelID,
                    message: toReturn
                });		
				break;	
				
			case 'help':
					bot.sendMessage({
                    to: channelID,
                    message: "!uwu (text) for uwu text generator\n!fact for a fun fact\n!ttt for tic tac toe\n!cat for cat pic"
					});
				break;
			
			case 'bean':
					bot.sendMessage({
                    to: channelID,
                    message: "check!"
					});
				break;
			
			case 'quit':
				if(user == "bunchiesonfire")
					process.exit();
				break;
			
			case 'tweet':
				if(user == "bunchiesonfire") {
					message = message.substring(7);
						
					client.post('statuses/update', {status: message},  function(error, tweet, response) {
						if(error) console.log(error);
						console.log(tweet);  // Tweet body.
						bot.sendMessage({
							to: channelID,
							message: "Tweeted!"
						});
					});
				}
				else {
					bot.sendMessage({
						to: channelID,
						message: "You are not bunchiesonfire so you can't tweet for him bro"
					});
				}
				break;
			
			case 'ttt':
				var num = parseInt(message.substring(5));
				if(!inGame && num != '' && num != 0 && num != null && num != NaN) {
					Board = [
						[0,0,0],
						[0,0,0],
						[0,0,0]
					];
					p1 = user;
					p2 = null;
					turn = 1;
					tttcount = 0;
					inGame = true;
				}
				if(num == '') {
					bot.sendMessage({
							to: channelID,
							message: "use [!ttt #] to play the specified spots:\n1  2  3\n4  5  6\n7  8  9"
						});
					break;
				}
				if(num > 9 || num < 1) {
					bot.sendMessage({
							to: channelID,
							message: "error: invalid input"
						});
					break;
				}
				num--;
				if(p2 == null && turn == 2) {
					p2 = user;
				}
				if(p1 == user && turn == 2 || p2 == user && turn == 1) {
					bot.sendMessage({
						to: channelID,
						message: "it aint your turn"
					});
					break;
				}

				if(user != p1 && user != p2) {
					bot.sendMessage({
						to: channelID,
						message: "we full"
					});
					break;	
				}
				var a = parseInt(num/3);
				var b = parseInt(num%3);
				if(Board[a][b] != 0) {
					bot.sendMessage({
						to: channelID,
						message: "invalid move"
					});
					print(Board);
				}
				else {
					tttcount++;
					console.log(tttcount);
					if(turn == 1) {
						Board[a][b] = 1;
						turn = 2;
					}
					else {
						Board[a][b] = 2;
						turn = 1;
					}
					print(Board);
					if(check(Board) == 0 && tttcount == 9) {
						bot.sendMessage({
							to: channelID,
							message: "it's a tie!"
						});
						inGame = false;
					}
					else if(check(Board) != 0) {
						bot.sendMessage({
							to: channelID,
							message: (check(Board) == 1 ? p1 : p2) + " wins!"
						});
						inGame = false;
					}  
					if(inGame && tttcount > 2)
						bot.sendMessage({
							to: channelID,
							message: "it's " + (turn == 1 ? p1 : p2) + "'s turn (" + (turn == 1 ? "O" : "X") + ")."
						});
				}	
			break;
		}

    }
    else if (message.includes("bunchie") && user != "BunchieBot") {
		message = message.toLowerCase();

        bot.sendMessage({
            to: channelID,
            message: "did someone say BUNCHIE???????????????"
		});
	}

	else {
		var toReply = '';
		var isReply = false;
		var count = 0;
		for(var i = 0; i < args.length; i++) {
			if(args[i].toLowerCase() == "im" || args[i].toLowerCase() == "i'm") {
				if(i < args.length) {
					toReply = message.substring(count+(args[i].toLowerCase() == "im" ? 3 : 4));
					isReply = true;
				}
			}
			count += args[i].length + 1;
		}
	
		if(isReply && user != "BunchieBot") {
			bot.sendMessage({
				to: channelID,
				message: "Hi " + toReply + ", I'm Bunchie"
			});		
		}
	}
});

function isVowel(a) {
	return (a == "a" || a == "e" || a == "i" || a == "o" || a == "u" || a == "y");
}

function print(board) {
	var str = "";
	for (var i = 0; i < 3; i++) { 
		for(var j = 0; j < 3; j++) {
			if(board[i][j] == 1)
				str += "O  ";
			if(board[i][j] == 2)
				str += "X  ";
			if(board[i][j] == 0)
				str += "-  ";
		}
		str += "\n"
	}
	bot.sendMessage({
		to: ID,
		message: str
	});	
}

function check(board) {
	var ans;
	for(var i = 0; i < 3; i++) {
		ans = board[i][0];
		if(board[i][1] == ans && board[i][2] == ans)
			return ans;
	}
	for(var i = 0; i < 3; i++) {
		ans = board[0][i];
		if(board[1][i] == ans && board[2][i] == ans)
			return ans;
	}
	ans = board[1][1];
	if(board[0][0] == ans && board[2][2] == ans || board[2][0] == ans && board[0][2] == ans)
		return ans;
	return 0;
}

