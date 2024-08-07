import React, { useState } from 'react';
import userImg from '../assets/photos/new.jpg';
import jsImg from '../assets/photos/javascript.jpeg';
import '../styles/Homepage.css';
import Post from '../components/Post';
import paginate from '../utils/paginate';
import Navbar from '../components/Navbar';

const HomePage = () => {
  const lonText = "Upon arrival, your senses will be rewarded with the pleasant scent of lemongrass oil uses to clean the natural wood found throughout the room, creating a relaxing atmosphere withing  the space. A wonderful serently has taken possession of my entire soul, like these sweet mornings of spring which I enjoy with the whole heart.I am alone , and feel the charm of existence in this spot, which was created for the bliss of souls like mine. I am so happy, my dear friend, so absorbed in the exquisite"

  const posts = [
    { title: 'Opening day of boating season, Seattle WA', content: lonText },
    { title: 'Opening day of boating season, Seattle WA', content: lonText },
    { title: 'Opening day of boating season, Seattle WA', content: lonText },
    { title: 'Opening day of boating season, Seattle WA', content: lonText },
    { title: 'Opening day of boating season, Seattle WA', content: lonText },
    { title: 'Opening day of boating season, Seattle WA', content: lonText },
    { title: 'Opening day of boating season, Seattle WA', content: lonText },
    { title: 'Opening day of boating season, Seattle WA', content: lonText },
    { title: 'Opening day of boating season, Seattle WA', content: lonText },
    { title: 'Opening day of boating season, Seattle WA', content: lonText },
    { title: 'Opening day of boating season, Seattle WA', content: lonText },
    { title: 'Opening day of boating season, Seattle WA', content: lonText },
    { title: 'Opening day of boating season, Seattle WA', content: lonText },
    { title: 'Opening day of boating season, Seattle WA', content: lonText },
  ];

  const [currentPage, setCurrentPage] = useState(1);
  const postsPerPage = 8;

  // Calculate the total number of pages
  const totalPages = Math.ceil(posts.length / postsPerPage);

  // Get the posts for the current page
  const currentPosts = paginate(postsPerPage, currentPage, posts)
  // Handle page change
  const handlePageChange = (pageNumber) => {
    setCurrentPage(pageNumber);
  };

  return (
    <div className='home-container'>
      <Navbar />

      <div className='welcome'>
        Welcome to U'r_Blog site where your posts are revealed in the real world by people from different corners of the world.
      </div>
      <div className='ground-photo'>
        <img src={jsImg} alt='not_found' />
      </div>

      <div className='post-container'>
        <h2>All Posts</h2>
        <div className='all-posts'>
          {currentPosts.map((post, index) => (
            <Post key={index} title={post.title} content={post.content} maxLength={80} displayTitle={true} />
          ))}
        </div>
        <div className='pagination'>
          {Array.from({ length: totalPages }, (_, index) => (
            <button
              key={index}
              onClick={() => handlePageChange(index + 1)}
            //   disabled={currentPage === index + 1}
            >
              {index + 1}
            </button>
          ))}
        </div>
      </div>
    </div>
  );
};

export default HomePage;
