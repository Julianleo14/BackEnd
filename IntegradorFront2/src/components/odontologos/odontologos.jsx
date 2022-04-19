import React, { useState, useEffect } from "react";
import { getOdontologos, deleteOdontologo } from "../../services/OdontologosService";

const Odontologos = () => {
    const [odontologos,setOdontologos] = React.useState([]);
    useEffect(() => {
        const fetchData = async () => {
            const misOdontologos = await getOdontologos();
            setOdontologos(misOdontologos.data)
            console.log( odontologos);
        };
        fetchData();
        return () => {
         setOdontologos([]);
        }
      }, [Odontologos])

    function handleDelete (id) {        
        if (window.confirm("Desea borrar el odontologo con ID: "+id+"?")) {
            alert("Odontologo eliminado")
            deleteOdontologo(id);
          }
          else(
              alert("Eliminación Cancelada")
          )
      }
    return(
        <>
        <h1>Lista de Odontologos</h1>
        {<table className="table">
            <thead>
                <td>Odontologo ID</td>
                <td>Nombre</td>
                <td>Apellido</td>
                <td>Matrícula</td>
                <td>Borrar</td>
            </thead>
        <tbody>
        {
            odontologos.map(
                odontologo => <tr key ={odontologo.id}>
                    <td>{odontologo.id}</td>
                    <td>{odontologo.nombre}</td>
                    <td>{odontologo.apellido}</td>
                    <td>{odontologo.matricula}</td>
                    <td><button onClick={()=>handleDelete(odontologo.id)}></button></td>
                </tr>
            )
        }
        </tbody>
        </table>}
        </>
    )
}
export default Odontologos;