import {useState} from "react";


const ToDoCreator = ({handleCreateItem}) =>{
    let [content, setContent] = useState('');

    const handleSubmit = (e) => {
        handleCreateItem(e, content);
    }

    return (
        <form onSubmit={(e) => handleSubmit}>
            <input
                value={content}
                placeholder='ToDo...'
                onChange={(e) => setContent(e.target.value)}
                />
            <button onClick={handleSubmit}>Submit</button>
        </form>
    )

}
export default ToDoCreator;