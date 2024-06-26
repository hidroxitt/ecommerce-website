const headerDOM = document.querySelector('header.header');
const heroDOM = document.getElementById('hero');

window.addEventListener('scroll', e => {
    const rect = heroDOM.getBoundingClientRect();
    if (rect.top < 0) {
        headerDOM.classList.remove('bg-white');
    }
    if (rect.bottom < 0) {
        headerDOM.classList.add('bg-white');
    }
})