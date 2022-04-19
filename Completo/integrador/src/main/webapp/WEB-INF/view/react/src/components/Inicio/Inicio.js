import React from "react";
import { Link } from "react-router-dom";

export default class Inicio extends React.Component {
  render() {
    return (
      <>
        <header className="App-header">
          <nav style={{ display: "inline-block" }}>
            <Link to="/odontologos">
              <button>Odontologos</button>
            </Link>
            <Link to="/pacientes">
              <button>Pacientes</button>
            </Link>
            <Link to="/turnos">
              <button>Turnos</button>
            </Link>
          </nav>
        </header>
      </>
    );
  }
}
