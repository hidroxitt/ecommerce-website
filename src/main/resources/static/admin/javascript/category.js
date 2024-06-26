const levelRowDOM = document.querySelectorAll('i[data-id]')

function main() {
    levelRowDOM.forEach(row => {
        row.addEventListener('click', e => {
            const { id } = row.dataset;
            document.querySelectorAll(`tr[parent=parentId${id}]`).forEach(a => {
                a.classList.toggle('d-none');
            })
            row.classList.toggle('show');
        })
    })
}

main();