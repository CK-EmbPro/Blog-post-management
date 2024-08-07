import React from 'react'
import "../styles/SinglePost.css"
import Navbar from '../components/Navbar'
import Post from '../components/Post'
import Comment from '../components/Comment'
import Footer from '../components/Footer'

const SinglePost = () => {
    const lonText = "Upon arrival, your senses will be rewarded with the pleasant scent of lemongrass oil uses to clean the natural wood found throughout the room, creating a relaxing atmosphere withing  the space. A wonderful serently has taken possession of my entire soul, like these sweet mornings of spring which I enjoy with the whole heart.I am alone , and feel the charm of existence in this spot, which was created for the bliss of souls like mine. I am so happy, my dear friend, so absorbed in the exquisite"
    const post = { title: 'Opening day of boating season, Seattle WA', content: lonText }

    const comments = [
        {author: 'ck_debrice', date: "2024 04 July", content: "A river or a lake island  may be called an Eyot or Alt. And A small Island off the coast may be called a holm."},
        {author: 'ck_debrice', date: "2024 04 July", content: "A river or a lake island  may be called an Eyot or Alt. And A small Island off the coast may be called a holm."},
        {author: 'ck_debrice', date: "2024 04 July", content: "A river or a lake island  may be called an Eyot or Alt. And A small Island off the coast may be called a holm."},
        {author: 'ck_debrice', date: "2024 04 July", content: "A river or a lake island  may be called an Eyot or Alt. And A small Island off the coast may be called a holm."},
        {author: 'ck_debrice', date: "2024 04 July", content: "A river or a lake island  may be called an Eyot or Alt. And A small Island off the coast may be called a holm."},
        {author: 'ck_debrice', date: "2024 04 July", content: "A river or a lake island  may be called an Eyot or Alt. And A small Island off the coast may be called a holm."},
        {author: 'ck_debrice', date: "2024 04 July", content: "A river or a lake island  may be called an Eyot or Alt. And A small Island off the coast may be called a holm."},
        {author: 'ck_debrice', date: "2024 04 July", content: "A river or a lake island  may be called an Eyot or Alt. And A small Island off the coast may be called a holm."},
        {author: 'ck_debrice', date: "2024 04 July", content: "A river or a lake island  may be called an Eyot or Alt. And A small Island off the coast may be called a holm."},
    ]
    return (
        <div className='singlepost-container'>
            <Navbar />
            <div className='post-container'>
                <h2>{post.title}</h2>
                <Post title={post.title} content={post.content} maxLength={post.content.length} displayTitle={false} />
            </div>

            <div className='comment-container'>
                <p>Comments</p>
                {comments.map((comment, index)=> <Comment key={index} author={comment.author} content={comment.content} date={comment.date} />)}
            </div>
            <Footer/>
        </div>
    )
}

export default SinglePost