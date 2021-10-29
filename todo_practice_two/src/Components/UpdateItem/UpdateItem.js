import {useState} from "react";

const UpdateItem = ({item, handleUpdateItem, handleEdit}) =>{
    let [editContent, setEditContent] = useState('');

    const handleSubmit = (e, item) =>{
        handleUpdateItem(item, editContent);
        handleEdit();
    }

    return(
        <div>
            <input
                value={editContent}
                onChange={(e) => setEditContent(e.target.value)}
            />
            <button onClick={(e) => handleSubmit(e, item)}>Submit</button>

        </div>
    )
}
export default UpdateItem;