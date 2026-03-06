const phonePrices = {
    "Galaxy-S21": 299990,
    "Galaxy-S22": 349990,
    "Galaxy-S23": 399990,
    "Galaxy-S24": 449990,
    "Huawei-P20": 149990,
    "Huawei-P30": 179990,
    "Huawei-P40": 219990,
    "Huawei-P50": 249990,
    "iPhone-12": 299990,
    "iPhone-13": 349990,
    "iPhone-14": 399990,
    "iPhone-15": 449990
};

const coupons = {
    "SAVE5NOW": 5,
    "DISCOUNT10": 10,
    "WELCOME15": 15,
    "SUMMER20": 20,
    "FALL5OFF": 5,
    "SPRING10": 10,
    "EXTRA15DEAL": 15,
    "FLASH20SALE": 20,
    "HAPPY5": 5,
    "MEGA15SAVE": 15
};

const itemList = document.querySelector("#item-list")
const cartList = document.querySelector("table")
const couponCode = document.querySelector("#coupon")
const discount = document.querySelector("#discount")
const filter = document.querySelector("#type")
const sum = document.querySelector("#sum")
const checkoutButton = document.querySelector("#checkout")

function formatPrice(unformattedPrice) {
    return new Intl.NumberFormat('hu-HU', {
        style: 'currency',
        currency: 'HUF',
        minimumFractionDigits: 0,
    }).format(Math.round(unformattedPrice));
}

function delegate(parent, type, selector, handler) {
    parent.addEventListener(type, function (event) {
        const targetElement = event.target.closest(selector);

        if (this.contains(targetElement)) {
            handler.call(targetElement, event);
        }
    });
}

//1
function listingItems() {
    phones = [];
    for (p in phonePrices) {
        phones.push({name: p, price: phonePrices[p]});
    }
    itemList.innerHTML = phones.map(phone => 
        `<div>
            <img src="assets/${phone.name}.png" />
            <p>${phone.name}</p>
            <span>${formatPrice(phone.price)}</span><button id="${phone.name}">Kosárba</button>
        </div>`
    ).join("")
}
listingItems();


//2
let cartListItems = [];

delegate(itemList, "click", "button", function(e) {
    let phoneName = this.id;
    let phonePrice = phonePrices[phoneName];
    cartListItems.push({name: phoneName, price: phonePrice});
    listCartItems();
    currentPrice = 0;
    for (c of cartListItems) {
        currentPrice += c.price;
    }
    priceUpdate();
})
function listCartItems() {
    cartList.innerHTML = cartListItems.map(item => `<tr><td>${item.name}</td><td>${formatPrice(item.price)}</td></tr>`);
}

//3
sum.innerHTML = formatPrice(0);
discount.innerHTML = formatPrice(0);
currentDiscount = 0;
currentPrice = 0;
couponCode.addEventListener("input", countDiscount);
function countDiscount() {
    for (c in coupons) {
        if (couponCode.value == c) {
            currentDiscount = coupons[c];
            console.log(currentDiscount)
        }
        else {
            currentDiscount = 0;
        }
    }
    discountUpdate();
    priceUpdate();
}
function discountUpdate() {
    if (currentDiscount > 0) {
        discount.innerHTML = formatPrice(-(currentPrice/100)*currentDiscount);
    }
    else {
        discount.innerHTML = formatPrice(0);
    }
}
function priceUpdate() {
    sum.innerHTML = formatPrice(currentPrice-(currentPrice/100)*currentDiscount);
}