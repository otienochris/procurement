import React, { useState } from 'react'
import styled from 'styled-components'
import { Link } from 'react-router-dom'
import * as FaIcons from 'react-icons/fa'
import * as AiIcons from 'react-icons/ai'
import { data } from './SidebarData'
import SubMenu from './SubMenu'


const Nav = styled.div`
  background: #15171c;
  height: 70px;
  display: flex;
  justify-content: flex-start;
  align-items: center;
`
const NavIcon = styled(Link)`
  margin-left: 2rem;
  font-size: 40px;
  height: 80px;
  display: flex;
  justify-content: flex-start;
  align-items: center;
`


const SidebarNav = styled.nav`
  background: #15171c;
  width: 250px;
  height: 100vh;
  display: flex;
  justify-content: center;
  top: 0;
  position: fixed;
  left: ${({ sidebar }) => ( sidebar ? '0' : '-100%')};
  transition: 450ms;
  z-index: 10;
`
const SidebarWrap = styled.div`
  width: 100%;
`


const Sidebar = () => {

    const [sidebar, setSidebar] = useState(false)

    const showSide = () => setSidebar(!sidebar)

    return (
        <>
         <Nav>
             <NavIcon to='#'>
                 <FaIcons.FaBars onClick={showSide} />
             </NavIcon>
        </Nav>   
        <SidebarNav sidebar={sidebar}>
            <SidebarWrap>
            <NavIcon to='#'>
                 <AiIcons.AiOutlineClose onClick={showSide}/>
             </NavIcon>
             {data.map((item, index) => {
               return(
               <SubMenu item={item} key={index}/>
               )
             })}
            </SidebarWrap>
        </SidebarNav>
        </>
    )
}

export default Sidebar
