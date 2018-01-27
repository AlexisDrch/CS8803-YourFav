var express = require('express');
var router = express.Router();
var Content = require('../src/Content');
var pixabay = require('pixabay-api'); // picture api
const AUTH_KEY = '7847802-34ec22dbc687fdf94827eaac2'

router.get('/:id?',function(req,res,next) {

  // if id is passed : list of user's favorites
  if(req.params.id) {
    Content.getFavoritesByUserId(req.params.id, function(err,rows) {
      if(err) {
        res.json(err);
      } else {
        res.json(rows);
      }
    });
  } 
  // if id is not passed : common list of content
  else {
    pixabay.searchImages(AUTH_KEY, 'puppy')
      .then((r) => res.json(r))
      .catch(function(error) {
        res.json(error);
      });
  }
});

// adding a content to list of user's favorite
router.post('/',function(req,res,next) {

  Content.addFavorite(req.body,function(err,count) {
    if(err) {
      res.json(err);
    } else {
      res.json(req.body);//or return count for 1 &amp;amp;amp; 0
    }
  });
}); 


 /*
 router.delete('/:id',function(req,res,next){
 
Task.deleteTask(req.params.id,function(err,count){
 
if(err)
  {
  res.json(err);
  }
  else
  {
  res.json(count);
  }
 
});
 });
 router.put('/:id',function(req,res,next){
 
Task.updateTask(req.params.id,req.body,function(err,rows){
 
if(err)
  {
  res.json(err);
  }
  else
  {
  res.json(rows);
  }
  });
 });*/
 module.exports = router;