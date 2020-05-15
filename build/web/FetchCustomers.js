class FetchCustomers {
    loadCustomers() {
        var url = "RetrieveCustomersFromRepresentative";


        var representative = document.getElementById("representativeSelect").value;

        if(representative === undefined) {
            representative = "Brunner";
        }

        console.log("Representative: " + representative);

        fetch(url, {
            method: "POST",
            headers: {"Accept": "application/json", "Content-Type": "application/json"},
            body: representative
        }).then(response => response.json()
        ).then(data => {
            console.log("Data: " + data);
            this.setupSelect(data);
        })

    }

    setupSelect(json) {
        var selectString = "";

        /*for (var element in json) {
            let optionText = (element.name == null) ? "id=" + element.id : element.name;
            selectString += '<option value="' + element.id + '">' + optionText + '</option>';
        }*/

        for (let i = 0; i < json.length; i++) {
            let optionText = (json[i].name == null) ? "id=" + json[i].id : json[i].name;
            selectString += '<option value="' + json[i].id + '">' + optionText + '</option>';
        }

        console.log("SelectString: " + selectString);

        document.getElementById("customerSelect").innerHTML = selectString;
    }
}