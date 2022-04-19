import React from "react";

import Inicio from "./components/Inicio/Inicio";
import "./styles/App.css";
import { BrowserRouter, Switch, Route } from "react-router-dom";
import Odontologos from "./components/odontologos/odontologos";
import Pacientes from "./components/pacientes/pacientes"
import Turnos from "./components/turnos/turnos"

const App = () => {
      return (
      <>
        {<BrowserRouter>
        <Inicio />
        <Switch>
          <Route exact path="/odontologos" component={Odontologos} />
          <Route exact path="/pacientes" component={Pacientes} />
          <Route exact path="/turnos" component={Turnos} />
         </Switch>
      </BrowserRouter>}
      </>
    );
  }

export default App;
