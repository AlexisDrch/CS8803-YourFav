var db = require('./dbconnection'); //reference of dbconnection.js
 
var user={

	getAllUsers:function(callback){
		return db.query("select * from `user`", callback);
	},
	getUserById:function(id, callback){
		return db.query("select * from `user` where id =?",[id],callback);
	},
	addUser:function(user,callback){
		return db.query("Insert into `user` values(?,?,?)",[null, user.name,user.surname],callback);
	}/*,
	/deleteTask:function(id,callback){
		return db.query("delete from task where Id=?",[id],callback);
	},
	updateTask:function(id,Task,callback){
		return db.query("update task set Title=?,Status=? where Id=?",[Task.Title,Task.Status,id],callback);
	}*/
};

module.exports=user;