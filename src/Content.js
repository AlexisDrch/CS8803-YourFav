var db = require('./dbconnection'); //reference of dbconnection.js

var content={

	ping:function(callback){
		return JSON.stringify({status: 'okay buddy'});
	},
	getFavoritesByUserId:function(id, callback){
		return db.query("select * from favorite" +
			" where `user`= ? ",[id],callback);
	},
	addFavoriteByUserandContentId:function(contentId,contentUrl,userId,callback){
		return db.query("Insert into `favorite` values(?,?,?)",[contentId,contentUrl,userId],callback);
	}/*,
	/deleteTask:function(id,callback){
		return db.query("delete from task where Id=?",[id],callback);
	},
	updateTask:function(id,Task,callback){
		return db.query("update task set Title=?,Status=? where Id=?",[Task.Title,Task.Status,id],callback);
	}*/
};

module.exports=content;