var db = require('./dbconnection'); //reference of dbconnection.js
 
var content={

	ping:function(callback){
		return JSON.stringify({status: 'okay buddy'});
	},
	getAllContents:function(callback){
		return db.query("select * from content", callback);
	},
	getFavoritesByUserId:function(id, callback){
		return db.query("select * from favorite where `user`=?",[id],callback);
	},
	addFavorite:function(user,content,callback){
		return db.query("Insert into `content` values(?,?)",[user,content],callback);
	}/*,
	/deleteTask:function(id,callback){
		return db.query("delete from task where Id=?",[id],callback);
	},
	updateTask:function(id,Task,callback){
		return db.query("update task set Title=?,Status=? where Id=?",[Task.Title,Task.Status,id],callback);
	}*/
};

module.exports=content;