import logo from './logo.svg';
import './App.css';
import { Outlet, useParams } from 'react-router-dom';
import Navbar from './components/Navbar'
import { useContext, useEffect } from 'react';
import { ProductContext } from './context/ProductContext';

function App() {
  const {filterProducts} = useContext(ProductContext);
  const {category} = useParams();
  useEffect(()=>{filterProducts(category)},[category])
  return (
    <div className='min-h-screen h-auto bg-slate-200 pb-40'>
      <Navbar/>
        <div className='w-[70%] m-auto p-10 bg-white my-4'>
      <Outlet/>
      </div>

    </div>
  );
}

export default App;
