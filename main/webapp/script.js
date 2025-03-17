async function addExpense(description, amount, paidBy, splitBetween) {
    const response = await fetch('/ExpenseSplitter/addExpense', {
        method: 'POST',
        headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
        body: `description=${description}&amount=${amount}&paidBy=${paidBy}&splitBetween=${splitBetween.join(",")}`
    });

    const result = await response.json();
    alert(result.status === "success" ? "Expense added successfully!" : "Failed to add expense.");
}

function submitExpense() {
    const description = document.getElementById('description').value;
    const amount = document.getElementById('amount').value;
    const paidBy = document.getElementById('paidBy').value;
    const splitBetween = document.getElementById('splitBetween').value.split(',');

    if (description && amount && paidBy && splitBetween.length > 0) {
        addExpense(description, amount, paidBy, splitBetween);
    } else {
        alert("Please fill all fields properly.");
    }
}

async function displayExpenses() {
    const response = await fetch('/ExpenseSplitter/getExpenses');
    const expenses = await response.json();

    const expenseList = document.getElementById('expenseList');
    expenseList.innerHTML = '';

    expenses.forEach(expense => {
        const div = document.createElement('div');
        div.innerHTML = `
            <strong>Description:</strong> ${expense.description} <br>
            <strong>Amount:</strong> ${expense.amount} <br>
            <strong>Paid By:</strong> ${expense.paidBy} <br>
            <strong>Split Between:</strong> ${expense.splitBetween.join(", ")} <br><br>
        `;
        expenseList.appendChild(div);
    });
}

async function showSettlement() {
    const response = await fetch('/ExpenseSplitter/calculateSettlement');
    const settlement = await response.json();

    const settlementDiv = document.getElementById('settlement');
    settlementDiv.innerHTML = '';

    Object.keys(settlement).forEach(member => {
        const amount = settlement[member];
        const div = document.createElement('div');
        div.innerHTML = `${member}: ${amount.toFixed(2)}`;
        settlementDiv.appendChild(div);
    });
}
