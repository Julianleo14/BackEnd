import axios from "axios";

const ODONTOLOGOS = 'http://localhost:8080/odontologos'

function getOdontologos(){
    return axios.get(ODONTOLOGOS);
}
function deleteOdontologo(id){
    return axios.delete(`${ODONTOLOGOS}/${id}`)
}

export { getOdontologos, deleteOdontologo};