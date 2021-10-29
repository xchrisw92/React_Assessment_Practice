import './App.css';
import {useEffect, useState} from "react";
import axios from "axios";
import Items from "./Components/Items/Items";
import ToDoCreator from "./Components/ToDoCreator/ToDoCreator";


function App() {
    let [items, setItems] = useState([]);
    let [createItem, setCreateItem] = useState(false);


    const updateItem = async (item, data) =>{
        await axios.patch(`http://localhost:3001/api/items/${item.id}`, data)
        getItems();
    }
    const getItems = () =>{
        axios.get('http://localhost:3001/api/items')
            .then(result => {
                setItems(result.data);
            })
    }
    useEffect(() => {
        getItems();
    }, [])


    const handleUpdateItem =async  (item, content) =>{
        let data ={content: content}
        await updateItem(item, data);
    }
    const handleCreateItem = async (e,content) =>{
        e.preventDefault();
        await axios.post('http://localhost:3001/api/items', {content: content})
        getItems();
        setCreateItem(false);
    }


    const handleDeleteItem = async (item) =>{
        await axios.delete(`http://localhost:3001/api/items/${item.id}`)
        getItems();
    }

    let handleCompletedUpdate = async (item) =>{
        let data = {completed: !item.completed};
        await updateItem(item, data);
    }
    return (
        <div className='bg-auto bg-gray-400 flex-auto h-64 justify-center items-center border-black border-2 shadow-md rounded-md'>
            <h1 className='text-4xl text-center font-sans font-semibold'>My To Do List</h1>
            <div className='flex-box justify-center p-4'>
                <Items
                    items={items}
                    handleDeleteItem={handleDeleteItem}
                    handleUpdateItem={handleUpdateItem}
                    handleCompletedUpdate={handleCompletedUpdate}
                />
            </div>
            <h3 onClick={()=>{setCreateItem(true)}}>Create New To Do Item</h3>
            {createItem ?
                <ToDoCreator handleCreateItem={handleCreateItem}/> :
                null
            }
        </div>
    );
}

export default App;
