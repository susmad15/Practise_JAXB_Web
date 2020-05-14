

class FetchRepresentatives {

    loadRepresentatives(){

        var country = document.getElementById("countrySelect").value;
        var url = "RetrieveRepresentativesFromCountry";

        console.log("Start: Loading Representatives")

        fetch(url, {
            method: 'post',
            headers: { 'Accept': 'application/json', 'Content-Type': 'application/json' },
            body: country
        }).then(response => response.json()).then(data => {
            console.log("Data: " + data);
            this.setupSelect(data);
        })
    }

    setupSelect (json) {
        let selectString = "";

        for (let i = 0; i < json.length; i++) {
            let optionText = (json[i].name == null) ? "id=" + json[i].id : json[i].name;
            selectString += '<option value="' + json[i].id + '">' + optionText + '</option>';
        }

        document.getElementById("representativeSelect").innerHTML = selectString;
    }

}