import React, { useState } from 'react'
import "../styles/Register.css"

const Register = () => {
    const [formData, setFormData] = useState({
        firstName: '',
        lastName: '',
        email: '',
        password: '',
        phoneNumber: ''
      });

      const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData({
          ...formData,
          [name]: value
        });
      };


  return (
    <div className='register-div'>
    <form className='register-form'>
      <h2>User registration</h2>
        <div>
            <label>FirstName</label>
            <input type="text" name='firstName' value={formData.firstName} onChange={handleChange} />
        </div>

        <div>
            <label>LastName</label>
            <input type="text" name='lastName' value={formData.lastName} onChange={handleChange} />
        </div>

        <div>
            <label>Email</label>
            <input type="text" name='email' value={formData.email} onChange={handleChange} />
        </div>

        <div>
            <label>PhoneNumber</label>
            <input type="text" name='phoneNumber' value={formData.phoneNumber} onChange={handleChange} />
        </div>


        <div>
            <label>Password</label>
            <input type="text" name='password' value={formData.password} onChange={handleChange} />
        </div>

        <button type='submit'>register</button>
        
    </form>
    </div>
  )
}

export default Register