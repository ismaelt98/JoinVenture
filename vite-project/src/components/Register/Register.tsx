import { useState } from "react";
import style from './register.module.css'
import { useNavigate } from 'react-router-dom';
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';

// function callRegisterEndPoint() {
//   var myHeaders = new Headers();
//   myHeaders.append("Content-Type", "application/json");

//   var raw = JSON.stringify({
//     "username": "test",
//     "alias": "test1111",
//     "email": "test@gmailo.cm",
//     "password": "a197f08204c0b0db8dc9aad1c3ea1971a041fbbcf0158a875ced74536d987585",
//     "phone": "121212",
//     "roleuser": "admin"
//   });

//   var requestOptions: RequestInit = {
//     method: 'PUT',
//     headers: myHeaders,
//     body: raw,
//     redirect: 'follow'
//   };

//   fetch("http://localhost:8080/users", requestOptions)
//     .then(response => response.text())
//     .then(result => console.log(result))
//     .catch(error => console.log('error', error));
// }

// Data form
interface FormData {
  username: string
  alias: string
  email: string
  password: string
  phone: string
  roleuser: string
}

function Register(): any {
  const [formData, setFormData] = useState<FormData>({
    username: '',
    alias: '',
    email: '',
    password: '',
    phone: '',
    roleuser: ''
  });
  
  const handleChange = (e: React.ChangeEvent<HTMLInputElement>): void => {
    const { name, value } = e.target;
    setFormData(prevState => ({
      ...prevState,
      [name]: value,
    }));
  };
  
  const handleSubmit = (e: React.FormEvent<HTMLFormElement>): void => {
    e.preventDefault();
    // Handle form submission with formData
    alert(formData.username);
    // You can send formData to an API endpoint, update state, etc.
  };

  return (
    <>
      <form className={style.signupForm} onSubmit={handleSubmit}>
        <div>
          dshfjkhdsjkhsjkafd
          <label>
            Username:
            <input
              type="text"
              name="username"
              value={formData.username}
              onChange={handleChange}
            />
          </label>
        </div>
      </form>
    </>
  );
}

export default Register;