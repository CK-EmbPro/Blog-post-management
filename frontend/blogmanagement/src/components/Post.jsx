import React from 'react'
import postImag from '../assets/photos/javascript.jpeg'
import '../styles/Homepage.css'
import { truncateText } from '../utils/truncateText'

const Post = ({title, content, maxLength, displayTitle}) => {
    const lonText = "Upon arrival, your senses will be rewarded with the pleasant scent of lemongrass oil uses to clean the natural wood found throughout the room, creating a relaxing atmosphere withing  the space. A wonderful serently has taken possession of my entire soul, like these sweet mornings of spring which I enjoy with the whole heart.I am alone , and feel the charm of existence in this spot, which was created for the bliss of souls like mine. I am so happy, my dear friend, so absorbed in the exquisite"
    return (
        <div className='post'>
            <img src={postImag} alt="not-found" />
            <h5>{displayTitle && title}</h5>
            <p>{truncateText(content, maxLength)}</p>
        </div>
    )
}

export default Post