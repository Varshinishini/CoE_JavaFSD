import React from 'react';
import { MdDoneOutline } from "react-icons/md";

export default function Sucess() {
  return (
    <div className='flex items-center justify-center font-bold gap-2 text-2xl'><MdDoneOutline className='text-green-600'/>Order places successfully.</div>
  )
}
