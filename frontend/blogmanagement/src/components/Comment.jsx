import React from 'react'
import userImg from '../assets/photos/new.jpg'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faCalendar } from '@fortawesome/free-solid-svg-icons'

const Comment = ({author, content, date}) => {
    
  return (
    <div className='comment'>
        <div className='image-author'>
            <img src={userImg} alt="" />
            <div className='author-date'>
                <p>{author}</p>
                <p><FontAwesomeIcon className='calendar' icon={faCalendar} /> {date}</p>
            </div>
        </div>

        <p className='comment-content'>{content }</p>
    </div>
  )
}

export default Comment