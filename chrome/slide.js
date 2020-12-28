

var slideIndex = [1,1];
var slideId = ["slide1", "slide2"]
showSlides(1, 0);
showSlides(1, 1);


function plusSlides(n, no) {
  showSlides(slideIndex[no] += n, no);
}


// display
function showSlides(n, element) {
  var i;
  var x = document.getElementsByClassName(slideId[element]); // number of class objects
  if (n > x.length) {slideIndex[element] = 1} ;  
  if (n < 1) {slideIndex[element] = x.length};
  for (i = 0; i < x.length; i++) {
     x[i].style.display = "none";  
  }
  x[slideIndex[element]-1].style.display = "block";  
}
