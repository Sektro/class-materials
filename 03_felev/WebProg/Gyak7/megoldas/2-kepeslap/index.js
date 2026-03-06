const form = document.querySelector("form");
const bgInput = document.querySelector("#background-image");
const addresseeInput = document.querySelector("#addressee");
const senderInput = document.querySelector("#sender");
const contentsFieldset = form.querySelector(".contents");
const contentTextarea = contentsFieldset.querySelector("textarea");
const alignRadio = form.elements["align1"];

const postcardSection = document.querySelector("#postcard");
const addresseeHeading = postcardSection.querySelector("h1");
const senderFooter = postcardSection.querySelector("footer");
const contentsDiv = postcardSection.querySelector(".contents");
const contentPar = contentsDiv.querySelector("p");

// =================== Eddig ==========================

form.addEventListener("input", function () {
  const bgImg = bgInput.value;
  const addressee = addresseeInput.value;
  const sender = senderInput.value;

  postcardSection.style.backgroundImage = bgImg !== "" ? `url('${bgImg}')` : "";
  addresseeHeading.innerHTML = addressee;
  senderFooter.innerHTML = sender;

  contentsDiv.innerHTML = "";
  const textarea = contentsFieldset.querySelector("textarea");
  const content = textarea.value;
  const radio = form.elements[contentsFieldset.querySelector("[type=radio]").name];
  const contentPar = document.createElement("p");
  contentPar.innerHTML = content;
  contentPar.classList.toggle("left", radio.value === "left");
  contentPar.classList.toggle("center", radio.value === "center");
  contentPar.classList.toggle("right", radio.value === "right");
  contentsDiv.appendChild(contentPar);
});
