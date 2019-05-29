using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class ConstrainedFollow : MonoBehaviour
{
	public Transform target;
	public Vector3 adjust;
	public Vector3 min, max;
	public bool x, y, z;
    // Start is called before the first frame update
    void Start()
    {
        
    }

    // Update is called once per frame
    void Update()
    {
        if(CameraControls.isTransitioning)
        {

        }
        else
        {
    		Vector3 newPos = transform.position;
		    if(x)
			    newPos.x = Mathf.Clamp(target.transform.position.x + adjust.x,min.x,max.x);
		    if (y)
			    newPos.y = Mathf.Clamp(target.transform.position.y + adjust.y, min.y, max.y);
		    if (z)
			    newPos.z = Mathf.Clamp(target.transform.position.z + adjust.z, min.z, max.z);
		    transform.position = newPos;
        }
	}
}
