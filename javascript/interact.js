
const colors = ["green", "red", "rgb(133,122,200)", "#f15025"];
const button = document.getElementById("btn");
const color = document.querySelector(".color");   // first element with class name "color"

let num = 0;
button.addEventListener("click", function() {
  let randomNum = randomNumber();
  if (randomNum == num) {
    if (randomNum == 0) randomNum ++;
    else if (randomNum == 3) randomNum --;
    else {randomNum ++};
  }
  console.log(randomNum);
  document.body.style.backgroundColor = colors[randomNum];
  color.textContent = colors[randomNum];

  num = randomNum;
});

function randomNumber() {
  return Math.floor(Math.random() * colors.length);
}
