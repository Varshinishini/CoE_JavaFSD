import React, { useContext } from 'react';
import { FaInfinity } from "react-icons/fa6";
import { Link, NavLink } from 'react-router-dom';
import { FaShoppingCart } from "react-icons/fa";
import { products_categories } from '../data/products';
import { ProductContext } from '../context/ProductContext';

export default function Navbar() {

  const { invoice } = useContext(ProductContext)

  const isActive = (element) => {
    return element?.isActive ? 'text-blue-600' : ''
  }

  return (
    <div className='w-full h-20 border shadow-lg flex items-center justify-between px-8 bg-white'>
      <NavLink className='flex flex-col items-center' to={'/'}>
        <FaInfinity className='text-red-500 text-4xl'/>
        <span>Shopnity</span>
      </NavLink>
        
      
      <ul className='flex items-center gap-10'>
        {
          products_categories.map(category=>{
            return(
              <li key={category.value}><NavLink className={isActive} to={`/${category.value}`}>{category.label}</NavLink></li>

            )
          })
        }
        
      </ul>
      <Link className='relative' to={'/cart'}>
      <FaShoppingCart className='text-4xl'/>
      {
        invoice?.count > 0 &&
        <div className=' absolute -top-2 -right-2 w-4 h-4 text-xs bg-blue-700 text-white flex items-center justify-center rounded-full'>
          {invoice?.count}
        </div>

      }

      </Link>

      
      
    </div>
  )
}

