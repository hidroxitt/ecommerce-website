const formDOM = document.querySelector('form');
const fromDateDOM = document.getElementById('from-date');
const toDateDOM = document.getElementById('to-date');

fromDateDOM.addEventListener('change', e => {
    if (toDateDOM.value) {
        formDOM.submit();
    }
});

toDateDOM.addEventListener('change', e => {
    if (fromDateDOM.value) {
        formDOM.submit();
    }
})