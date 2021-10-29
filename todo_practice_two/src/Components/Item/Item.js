import {useState} from "react";
import UpdateItem from "../UpdateItem/UpdateItem";

const Item = ({item, handleDeleteItem, handleUpdateItem, handleCompletedUpdate}) => {
    let [editBoolean, setEditBoolean] = useState(false);

    const handleEditClick = () => {
        setEditBoolean(!editBoolean);
    }
    const handleDeleteClick = (item) => {
        handleDeleteItem(item);
    }

    const handleCompletedClick = () => {
        handleCompletedUpdate(item);
    }

    return (
        <div className='container '>
            {
                item.completed ?
                    <span><del>{item.content}</del></span> :
                    <span>{item.content}</span>
            }
            <input
                value={item.completed}
                onClick={(e) => handleCompletedClick(item)}
                type='checkbox'
                id='completed'

            />
            <label htmlFor='completed'>Completed</label>


            <div>
                <button onClick={(e) => handleDeleteClick(item)}>Delete</button>
                <button onClick={handleEditClick}>Edit</button>
            </div>
            {editBoolean ?
                <UpdateItem item={item} handleUpdateItem={handleUpdateItem} handleEdit={handleEditClick}/>
                : null
            }
        </div>
    )

}
export default Item;