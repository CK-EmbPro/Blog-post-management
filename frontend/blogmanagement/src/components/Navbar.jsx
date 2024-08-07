import React from 'react'
import userImg from '../assets/photos/new.jpg';

const Navbar = () => {
  return (
    <nav className='home-navbar'>
        <h1>U'r_Blog</h1>
        <h3>All_posts</h3>
        <h3>new_post</h3>

        <div className='user_profile'>
          <img src={userImg} className='userImg' alt='not_found' />
          <p>ck_debrice</p>
        </div>
      </nav>
  )
}

export default Navbar