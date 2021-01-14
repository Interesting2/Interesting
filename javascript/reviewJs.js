
const review = [
  {
    id: 1,
    name: "skymode",
    job: "UX UI Designer",
    img: "./skymode.jpeg",
    text: "Watashi no namae wa Skymode desu. I'm a Junior Front End Developer, experienced with Html, Css, Javascript, \
          and UI/UX designs. In addition, I prefer to edit videos and play around with adobe softwares."

  },

  {
    id: 2,
    name: "Mingi",
    job: "Marketing",
    img: "./pig.png",
    text: "Watashi no namae wa Mingi desu. I've been working as a marketing manager for 5 years in various big technology \
          companies, such as Google, Microsoft, Facebook, Amazon, Uber."
  },

  {
    id: 3,
    name: "Jackson",
    job: "Front End Developer",
    img: "./punchman.png",
    text: "Watashi no namae wa Jackson desu. I started html and css one year ago. \
           I'm currently learning some javascript algorithms, in order to enhance the overall User Interface Experience."
  }


];

const img = document.getElementById("person-img");
const author = document.getElementById("author");
const occupation = document.getElementById("occupation");
const description = document.getElementById("description");

const prevBtn = document.querySelector(".prev-button");
const nextBtn = document.querySelector(".next-button");
const otherBtn = document.querySelector(".other-button");

let curr = 0;

window.addEventListener("DOMContentLoaded", function()  {
  //console.log("Document loaded");
  setReview(curr);
});

nextBtn.addEventListener("click", function() {
  curr ++;
  if (curr > review.length - 1) curr = 0;
  setReview(curr);
});

prevBtn.addEventListener("click", function() {
  curr --;
  if (curr < 0) curr = review.length - 1;
  setReview(curr);
});






function setReview(index) {
  const item = review[index];
  img.src = item.img;
  author.textContent = item.name;
  occupation.textContent = item.job;
  description.textContent = item.text;

}
