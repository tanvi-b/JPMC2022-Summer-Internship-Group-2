import './App.css';

console.log("Application has started.");

function App() {
  return (

    <div className="App">
      <div className="toolbar" role="banner">
        <img width="40" height=" 30" alt="JPMC Logo PLaceholder"
          src="https://th.bing.com/th/id/OIP.42US39t41lKv7d-hqR7ImQHaE8?pid=ImgDet&rs=1"></img>
        
        
        <h2>Cassandra Clusters Application</h2>
        
        

        <div className="spacer"></div>

      </div>

      <div className="row">
        <div className="column1">
          <div className="content" role="main">

            <h2>Table Information</h2>
            <label>
              Click To Add Table Data
              <input type="file" accept=".csv" id="fileUpload" onClick={csvtoJS} class = "input input1"/>
            </label>
              
            
            
            
            <table id="table-stats">
              <tr>
                <th>Keyspace Name</th>
                <th>Table Name</th>
                <th>Num Partitions</th>
                <th>Partition Row Stats</th>
                <th>Column Definitions</th>
                <th>Table Size</th>
                <th>Partition Size Stats</th>
              </tr>
            </table>
            


          </div>
        </div>
        <div class="column2">

        </div>
        <div class="column3">
          <div class="sidebar-menu">
            <div class="logo">
              <br></br>
              <br></br>
              <br></br>
              <br></br>
              <h2>Cluster Information</h2>
            </div>
            <section class="container">
              
              <label>
                Click To Add Cluster Information
                <input type="file" name="inputfile" id="inputfile" class = "input input2"/>
              </label>
              
              <br></br>

              <pre id="output"></pre>

            </section>
          </div>
        </div>

      </div>
    </div >
  );
}



function csvtoJS() {
  const x = document.querySelector("input");

  x.addEventListener("change", () => {

    const fr = new FileReader();

    fr.onloadend = e => {

      let r = fr.result.split("\n").map(e => {
        return e.split(",")
      });

      r.forEach(e => {

        let m = e.map(e => {
          return `<td>${e}</td>`;
        }).join("");

        console.log(m);
        console.log(r);

        const ce = document.createElement("tr");

        ce.innerHTML = m;

        if (ce.innerText !== "") {
          document.querySelector("table").append(ce);
        }

      });
    }
    fr.readAsText(x.files[0]);

  })
}

window.onload = function () {
  var t = document.getElementById('inputfile');

  if (t) {
    t.addEventListener('change', () => {

      var fr = new FileReader();
      fr.onload = function () {
        document.getElementById('output').textContent = fr.result;
      }
      fr.readAsText(t.files[0]);
    })
  } else {
    alert("ID not found.");
  }
}




export default App;
