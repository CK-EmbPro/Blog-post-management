const paginate = (postsPerPage, currentPage, posts) =>{
    return posts.slice(
        (currentPage - 1) * postsPerPage,
        currentPage * postsPerPage
      );
    
}

export default paginate;