import React, { useState } from 'react'
import "../styles/Login.css"

const Login = () => {

    const [loginData, setLoginData] = useState({
        email: '',
        password: ''
      });

      const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData({
          ...formData,
          [name]: value
        });
      };

  return (
    <div className='login-div'>
          <form className='login-form'>
         <div>
            <label>Email</label>
            <input type="text" name='email' value={loginData.email} onChange={handleChange} />
        </div>

    
        <div>
            <label>Password</label>
            <input type="text" name='password' value={loginData.password} onChange={handleChange} />
        </div>

        <button type='submit'>login</button>

    </form>
    </div>

  )
}

export default Login